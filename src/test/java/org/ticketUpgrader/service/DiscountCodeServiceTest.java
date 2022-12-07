package org.ticketUpgrader.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCodeServiceTest {
    @Test
    public void shouldGetDiscountCodeAsPerFareClass(){
        assertEquals("OFFER_20",DiscountCodeService.getDiscountCode("A"));
        assertEquals("OFFER_20",DiscountCodeService.getDiscountCode("C"));
        assertEquals("OFFER_20",DiscountCodeService.getDiscountCode("E"));
        assertEquals("OFFER_30",DiscountCodeService.getDiscountCode("F"));
        assertEquals("OFFER_30",DiscountCodeService.getDiscountCode("I"));
        assertEquals("OFFER_30",DiscountCodeService.getDiscountCode("K"));
        assertEquals("OFFER_25",DiscountCodeService.getDiscountCode("L"));
        assertEquals("OFFER_25",DiscountCodeService.getDiscountCode("P"));
        assertEquals("OFFER_25",DiscountCodeService.getDiscountCode("R"));
        assertEquals("",DiscountCodeService.getDiscountCode("Z"));
    }

}