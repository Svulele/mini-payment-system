

import com.sbulele.engine.SwitchEngine;
import com.sbulele.model.PaymentRequest;
import com.sbulele.model.PaymentResponse;
import com.sbulele.model.PaymentStatus;
import com.sbulele.provider.BankAProvider;
import com.sbulele.provider.BankBProvider;
import com.sbulele.provider.MobileWalletProvider;
import com.sbulele.router.SwitchRouter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwitchEngineTest {

    private SwitchEngine engine;

    @BeforeEach
    void setup() {
        SwitchRouter router = new SwitchRouter();
        router.registerProvider("BANKA", new BankAProvider());
        router.registerProvider("BANKB", new BankBProvider());
        router.registerProvider("WALLET", new MobileWalletProvider());
        engine = new SwitchEngine(router);
    }

    @Test
    void testValidBankAPayment() {
        PaymentRequest request = new PaymentRequest("CUST001", "BANKA", 3000);
        PaymentResponse response = engine.handlePayment(request);
        assertEquals(PaymentStatus.SUCCESS, response.getStatus());
    }

    @Test
    void testBankBExceedsLimit() {
        PaymentRequest request = new PaymentRequest("CUST002", "BANKB", 15000);
        PaymentResponse response = engine.handlePayment(request);
        assertEquals(PaymentStatus.FAILED, response.getStatus());
        assertEquals("BankB limit exceeded", response.getMessage());
    }

    @Test
    void testMobileWalletPayment() {
        PaymentRequest request = new PaymentRequest("CUST003", "WALLET", 500);
        PaymentResponse response = engine.handlePayment(request);
        assertEquals(PaymentStatus.SUCCESS, response.getStatus());
    }

    @Test
    void testInvalidAmount() {
        PaymentRequest request = new PaymentRequest("CUST004", "BANKA", -10);
        PaymentResponse response = engine.handlePayment(request);
        assertEquals(PaymentStatus.FAILED, response.getStatus());
        assertEquals("Invalid amount", response.getMessage());
    }

    @Test
    void testUnknownProvider() {
        PaymentRequest request = new PaymentRequest("CUST005", "UNKNOWN", 100);
        PaymentResponse response = engine.handlePayment(request);
        assertEquals(PaymentStatus.FAILED, response.getStatus());
        assertEquals("Unknown provider", response.getMessage());
    }
}
