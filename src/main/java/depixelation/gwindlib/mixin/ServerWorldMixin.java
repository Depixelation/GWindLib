package depixelation.gwindlib.mixin;

import depixelation.gwindlib.GlobalWindLib;
import depixelation.gwindlib.WindyWorld;
import depixelation.gwindlib.util.Debug;
import depixelation.gwindlib.util.WindCalculator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements WindyWorld {
	@Unique
	private Vec3d windVector;

	@Override
	public Optional<Vec3d> getWind() {
		return Optional.ofNullable(windVector);
	}

    @Shadow
    public abstract long getSeed();

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/server/world/ServerWorld;tickWeather()V")
	public void tickWind(CallbackInfo ci){
		windVector = WindCalculator.calculate(((ServerWorld) (Object) this).getTimeOfDay(), getSeed(), (World) (Object) this);
		if (((World) (Object) this).getTimeOfDay() % 40 == 0){
			debug("" + windVector.length());
		}
	}

	@Unique
	private void debug(String msg){
		Debug.send(msg);
	}
}