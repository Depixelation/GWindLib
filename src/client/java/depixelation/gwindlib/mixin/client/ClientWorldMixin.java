package depixelation.gwindlib.mixin.client;

import depixelation.gwindlib.ClientWorldInterface;
import depixelation.gwindlib.GlobalWindLib;
import depixelation.gwindlib.WindyWorld;
import depixelation.gwindlib.config.Constants;
import depixelation.gwindlib.util.Debug;
import depixelation.gwindlib.util.WindCalculator;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ClientWorld.class)
public class ClientWorldMixin implements WindyWorld, ClientWorldInterface {
	@Unique
	Long seed;

	@Unique
	Vec3d windVector;

	@Override
	public Optional<Vec3d> getWind() {
		return Optional.ofNullable(windVector);
	}

	@Override
	public Optional<Long> gWindLib$getSeed() {
		return Optional.ofNullable(seed);
	}

	@Override
	public void gWindLib$setSeed(long seed) {
		this.seed = seed;
	}

	@Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/world/ClientWorld;tick(Ljava/util/function/BooleanSupplier;)V")
	public void tickWind(CallbackInfo ci){
		Optional<Long> seed = gWindLib$getSeed();
		if(seed.isPresent()) windVector = WindCalculator.calculate(((ClientWorld) (Object) this).getTimeOfDay(), seed.get(), (World) (Object) this);
		else {
			ClientPlayNetworking.send(Constants.WIND_SEED_PACKET_ID, PacketByteBufs.empty());
			debug("packet sent");
		}

		if (((World) (Object) this).getTimeOfDay() % 40 == 0 && windVector != null){
			debug("" + windVector.length());
		} else if (windVector == null){
			debug("windVector null");
		}
	}

	@Unique
	private void debug(String msg){
		Debug.send(msg);
	}
}