package com.gildedrose;

import java.util.Arrays;

class GildedRose {

    private final int MAXIMUM_QUALITY = 50;
    private final int MINIMAL_QUALITY = 0;
    private final int DEFAULT_QUALITY_MODIFIER = -1;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        Arrays.stream(items).forEach(item -> {
            item.sellIn--;
            int modifier = DEFAULT_QUALITY_MODIFIER;
            if (item.name.contains("Aged Brie")) {
                modifier = Math.abs(modifier); // set modifier to positive
            }

            if (item.name.contains("Backstage passes")) {
                modifier = backStagePassesValueModifier(item); // set to specific valye
            }

            if (item.name.contains("Conjured")) { // multiplies modifier by two when conjured
                modifier *= 2;
            }

            if (item.name.contains("Sulfuras")) {
                return; // We stop before quality value modification
            }

            updateQuality(item, modifier);

        });
    }

    private void updateQuality(Item item, int modifier) {
        int value = item.quality + (item.sellIn > 0 ? modifier : modifier * 2);
        item.quality = value <= MINIMAL_QUALITY ? MINIMAL_QUALITY : Math.min(value, MAXIMUM_QUALITY);

    }

    private int backStagePassesValueModifier(Item item) {
        if (item.sellIn < 0) { // concert is over
            return Integer.MIN_VALUE;
        } else if (item.sellIn <= 5) { // 5 days or less left
            return 3;
        } else if (item.sellIn <= 10) { // ten days or less left
            return 2;
        } else {
            return -DEFAULT_QUALITY_MODIFIER; // give the inverse value of the Default value
        }
    }

}
