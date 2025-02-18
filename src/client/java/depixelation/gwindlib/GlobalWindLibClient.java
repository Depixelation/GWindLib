package depixelation.gwindlib;

import depixelation.gwindlib.config.Constants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class GlobalWindLibClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		Constants.initConfig();

		ClientPlayNetworking.registerGlobalReceiver(Constants.WIND_SEED_PACKET_ID, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
			long seed = packetByteBuf.readLong();
			minecraftClient.execute(() -> {
				((ClientWorldInterface) (Object) minecraftClient.world).gWindLib$setSeed(seed);
			});
		});
	}

	public static Optional<Vec3d> getWind(ClientWorld world){
		return ((WindyWorld) world).getWind();
	}
}