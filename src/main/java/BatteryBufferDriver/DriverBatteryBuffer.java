package BatteryBufferDriver;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import li.cil.oc.api.driver.DriverBlock;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import gregtech.common.metatileentities.electric.MetaTileEntityBatteryBuffer;

/**
 * @author Jonas Frey
 * @version 1.0
 */
public class DriverBatteryBuffer implements DriverBlock {

    @Override
    public boolean worksWith(World world, BlockPos blockPos, EnumFacing enumFacing) {
        return getBuffer(world, blockPos) != null;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos blockPos, EnumFacing enumFacing) {

        MetaTileEntityBatteryBuffer buffer = getBuffer(world, blockPos);
        // getBuffer doesn't return nil, otherwise worksWith would have returned false
        IEnergyContainer energyContainer = buffer.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, enumFacing);

        return new EnvironmentBatteryBuffer(buffer, energyContainer, enumFacing);

    }
    
    private MetaTileEntityBatteryBuffer getBuffer(World world, BlockPos blockPos) {
        TileEntity tile = world.getTileEntity(blockPos);
        if (tile != null) {
            if (tile instanceof MetaTileEntityHolder) {
                MetaTileEntityHolder holder = (MetaTileEntityHolder) tile;
                if (holder.getMetaTileEntity() instanceof MetaTileEntityBatteryBuffer) {
                    return (MetaTileEntityBatteryBuffer) holder.getMetaTileEntity();
                }
            }
        }
        return null;
    }
    
}
