package depixelation.gwindlib;

import depixelation.gwindlib.config.Constants;
import depixelation.gwindlib.util.Debug;
import depixelation.gwindlib.util.WindCalculator;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GlobalWindLib implements ModInitializer {
	public static final String MOD_ID = "gwindlib";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerPlayConnectionEvents.JOIN.register((serverPlayNetworkHandler, packetSender, minecraftServer) -> {
			PacketByteBuf buf = PacketByteBufs.create();
			buf.writeLong(serverPlayNetworkHandler.getPlayer().getServerWorld().getSeed());

			minecraftServer.execute(() -> {
				ServerPlayNetworking.send(serverPlayNetworkHandler.getPlayer(), Constants.WIND_SEED_PACKET_ID, buf);
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(Constants.WIND_SEED_PACKET_ID, (minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) ->  {
			Debug.send("Packet recieved");
			PacketByteBuf buf = PacketByteBufs.create();
			buf.writeLong(serverPlayerEntity.getServerWorld().getSeed());

			minecraftServer.execute(() -> {
				ServerPlayNetworking.send(serverPlayNetworkHandler.getPlayer(), Constants.WIND_SEED_PACKET_ID, buf);
			});
		});
	}

	public static Optional<Vec3d> getWind(ServerWorld world){
		return ((WindyWorld) world).getWind();
	}
}