package de.maxhenkel.pipez.net;

import de.maxhenkel.corelib.net.Message;
import de.maxhenkel.pipez.gui.ExtractContainer;
import de.maxhenkel.pipez.gui.FilterContainer;
import de.maxhenkel.pipez.gui.containerfactory.PipeContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.network.NetworkEvent;

public class OpenExtractMessage implements Message<OpenExtractMessage> {

    public OpenExtractMessage() {

    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        Container container = context.getSender().openContainer;
        if (container instanceof FilterContainer) {
            FilterContainer filterContainer = (FilterContainer) container;
            PipeContainerProvider.openGui(context.getSender(), filterContainer.getPipe(), filterContainer.getSide(), (id, playerInventory, playerEntity) -> new ExtractContainer(id, playerInventory, filterContainer.getPipe(), filterContainer.getSide()));
        }
    }

    @Override
    public OpenExtractMessage fromBytes(PacketBuffer packetBuffer) {
        return this;
    }

    @Override
    public void toBytes(PacketBuffer packetBuffer) {

    }
}