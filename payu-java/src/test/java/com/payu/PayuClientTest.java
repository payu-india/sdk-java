package com.payu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
class PayuTest {
    PayuClient payuClient = PayuClient.init("bjg$121", "vhgvs75");
    PayuClient payuClient2 = PayuClient.init("bjg$121", "vhgvs75");
    /**
     * Rigorous Test.
     */
    @Test
    void testPayU() {
      assertTrue(payuClient.hasher instanceof Hasher);
    }

    @Test
    void testSingleton() {
      assertTrue(payuClient == payuClient2);
    }
}
