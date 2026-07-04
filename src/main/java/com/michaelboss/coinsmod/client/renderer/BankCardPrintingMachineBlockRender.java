package com.michaelboss.coinsmod.client.renderer;

import com.michaelboss.coinsmod.blockentity.BankCardPrintingMachineBlockEntity;
import com.michaelboss.coinsmod.client.geckolib.BankCardPrintingMachineBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BankCardPrintingMachineBlockRender extends GeoBlockRenderer<BankCardPrintingMachineBlockEntity> {
    public BankCardPrintingMachineBlockRender() {
        super(new BankCardPrintingMachineBlockGeoModel());
    }
}
