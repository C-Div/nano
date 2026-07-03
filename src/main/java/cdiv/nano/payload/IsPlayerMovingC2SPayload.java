package cdiv.nano.payload;

import cdiv.nano.Nano;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record IsPlayerMovingC2SPayload(boolean isMoving) implements CustomPayload {
    public static final CustomPayload.Id<IsPlayerMovingC2SPayload> ID = new CustomPayload.Id<>(Nano.id("is_player_moving"));
    public static final PacketCodec<RegistryByteBuf, IsPlayerMovingC2SPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, IsPlayerMovingC2SPayload::isMoving, IsPlayerMovingC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}