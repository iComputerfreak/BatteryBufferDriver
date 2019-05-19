package BatteryBufferDriver;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.capability.IEnergyContainer;
import gregtech.common.metatileentities.electric.MetaTileEntityBatteryBuffer;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.AbstractManagedEnvironment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/**
 * @author Jonas Frey
 * @version 1.0
 */
public class EnvironmentBatteryBuffer extends AbstractManagedEnvironment implements NamedBlock {

    protected String name;
    protected MetaTileEntityBatteryBuffer buffer;
    protected IEnergyContainer energyContainer;
    protected EnumFacing enumFacing;

    @Override
    public String preferredName() {
        return name;
    }

    @Override
    public int priority() {
        return 5;
    }

    public EnvironmentBatteryBuffer(MetaTileEntityBatteryBuffer buffer, IEnergyContainer energyContainer, EnumFacing enumFacing)
    {
        this.name = "battery_buffer";
        this.buffer = buffer;
        this.energyContainer = energyContainer;
        this.enumFacing = enumFacing;
        setNode(Network.newNode(this, Visibility.Network).withComponent(name, Visibility.Network).create());
    }

    @Callback(doc = "function(): int - Gets the charge of the battery buffer")
    public Object[] getCharge(final Context context, Arguments arguments) {
        return new Object[] { energyContainer.getEnergyStored() };
    }

    @Callback(doc = "function(): int - Gets the capacity of the battery buffer")
    public Object[] getCapacity(final Context context, Arguments arguments) {
        return new Object[] { energyContainer.getEnergyCapacity() };
    }

    @Callback(doc = "function(): int - Gets the input voltage of the battery buffer")
    public Object[] getInputVoltage(final Context context, Arguments arguments) {
        return new Object[] { energyContainer.getInputVoltage() };
    }

    @Callback(doc = "function(): int - Gets the output voltage of the battery buffer")
    public Object[] getOutputVoltage(final Context context, Arguments arguments) {
        return new Object[] { energyContainer.getOutputVoltage() };
    }

    @Callback(doc = "function(): int - Gets the input amperage of the battery buffer")
    public Object[] getInputAmperage(final Context context, Arguments arguments) {
        return new Object[] { energyContainer.getInputAmperage() };
    }

    @Callback(doc = "function(): int - Gets the output amperage of the battery buffer")
    public Object[] getOutputAmperage(final Context context, Arguments arguments) {
        return new Object[] { energyContainer.getOutputAmperage() };
    }

    @Callback(doc = "function(): int - Gets the number of battery slots of the battery buffer")
    public Object[] getBatterySlots(final Context context, Arguments arguments) {
        return new Object[] { buffer.getItemInventory().getSlots() };
    }
    
    @Callback(doc = "function(index: int): int - Gets the charge of a single battery of the battery buffer at the given index")
    public Object[] getSingleCharge(final Context context, Arguments arguments) {
        int index = arguments.checkInteger(0);

        if (index < 0 || index >= buffer.getItemInventory().getSlots()) {
            return new Object[] { null, "Index out of bounds" };
        }
        
        ItemStack item = buffer.getItemInventory().getStackInSlot(index);
        IElectricItem electricItem = item.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, enumFacing);

        if (electricItem == null) {
            return new Object[] { null };
        }

        return new Object[] { electricItem.getCharge() };
    }

    @Callback(doc = "function(index: int): int - Gets the capacity of a single battery of the battery buffer at the given index")
    public Object[] getSingleCapacity(final Context context, Arguments arguments) {
        int index = arguments.checkInteger(0);
        
        if (index < 0 || index >= buffer.getItemInventory().getSlots()) {
            return new Object[] { null, "Index out of bounds" };
        }
        
        ItemStack item = buffer.getItemInventory().getStackInSlot(index);
        IElectricItem electricItem = item.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, enumFacing);

        if (electricItem == null) {
            return new Object[] { null };
        }

        return new Object[] { electricItem.getMaxCharge() };
    }

    @Callback(doc = "function(): int - Gets the number of batteries in the battery buffer")
    public Object[] getBatteryCount(final Context context, Arguments arguments) {
        int count = 0;
        
        for (int i = 0; i < buffer.getItemInventory().getSlots(); i++) {
            if (!buffer.getItemInventory().getStackInSlot(i).isEmpty()) {
                count++;
            }
        }

        return new Object[] { count };
    }
    
}
