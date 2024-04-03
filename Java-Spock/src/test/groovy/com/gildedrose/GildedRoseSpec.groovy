package com.gildedrose

import spock.lang.Specification

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {

    def "should update quality of aged brie"() {

        given: "the application"
        GildedRose app = new GildedRose(list as Item[])

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].sellIn == sellIn
        app.items[0].quality == quality

        where:
        list                            | sellIn | quality
        [new Item("Aged Brie", 0, 0)]   | -1     | 2
        [new Item("Aged Brie", 5, 10)]  | 4      | 11
        [new Item("Aged Brie", 10, 50)] | 9      | 50
    }

    def "should update quality of Back stages passes"() {

        given: "the application"
        GildedRose app = new GildedRose(list as Item[])

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].sellIn == sellIn
        app.items[0].quality == quality

        where:
        list                                                 | sellIn | quality
        [new Item("Backstage passes michel sardou", 0, 0)]   | -1     | 0
        [new Item("Backstage passes michel sardou", 6, 10)]  | 5      | 13
        [new Item("Backstage passes michel sardou", 11, 10)] | 10     | 12
        [new Item("Backstage passes michel sardou", 13, 10)] | 12     | 11
    }

    def "should update quality of conjured"() {

        given: "the application"
        GildedRose app = new GildedRose(list as Item[])

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].sellIn == sellIn
        app.items[0].quality == quality

        where:
        list                               | sellIn | quality
        [new Item("Conjured Brie", 0, 0)]  | -1     | 0
        [new Item("Conjured Brie", 5, 10)] | 4      | 8
        [new Item("Conjured Brie", 0, 50)] | -1     | 46
    }

    def "should update quality of composite items"() {

        given: "the application"
        GildedRose app = new GildedRose(list as Item[])

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].sellIn == sellIn
        app.items[0].quality == quality

        where:
        list                                                      | sellIn | quality
        [new Item("Conjured Brie", 0, 10)]                        | -1     | 6
        [new Item("Conjured Brie", 5, 10)]                        | 4      | 8
        [new Item("Conjured Aged Brie", 5, 10)]                   | 4      | 12
        [new Item("Backstage passes Conjured Aged Brie", 10, 10)] | 9      | 14
    }

    def "should never update quality of Sulfuras"() {

        given: "the application"
        GildedRose app = new GildedRose(list as Item[])

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].sellIn == sellIn
        app.items[0].quality == quality

        where:
        list                                    | sellIn | quality
        [new Item("Sulfuras Aged Brie", 0, 80)] | -1     | 80
        [new Item("Sulfuras", 5, 80)]           | 4      | 80
        [new Item("Conjured Sulfuras", 10, 80)] | 9      | 80
    }


}
