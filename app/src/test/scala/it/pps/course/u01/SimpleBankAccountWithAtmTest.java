package it.pps.course.u01;

import org.junit.jupiter.api.*;
import it.pps.course.u01.model.AccountHolder;
import it.pps.course.u01.model.SimpleBankAccountWithAtm;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private SimpleBankAccountWithAtm bankAccountWithAtm;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccountWithAtm = new SimpleBankAccountWithAtm(accountHolder, 0, 1);
        init(accountHolder, bankAccountWithAtm);
    }

    @Test
    void testInitialFees(){
        assertEquals(1, bankAccountWithAtm.getFees());
    }

    @Test
    void testDepositWithAtm(){
        bankAccountWithAtm.depositWithAtm(accountHolder.getId(), 100);
        assertEquals(99, bankAccountWithAtm.getBalance());
    }

    @Test
    void testDepositZeroDollarsWithAtm(){
        bankAccountWithAtm.depositWithAtm(accountHolder.getId(), 0);
        assertEquals(0, bankAccountWithAtm.getBalance());
    }

    @Test
    void testWithdrawWithAtm(){
        bankAccountWithAtm.deposit(accountHolder.getId(), 200);
        bankAccountWithAtm.withdrawWithAtm(accountHolder.getId(), 100);
        assertEquals(99, bankAccountWithAtm.getBalance());
    }

    @Test
    void testWithdrawZeroDollarsWithAtm(){
        bankAccountWithAtm.withdrawWithAtm(accountHolder.getId(), 0);
        assertEquals(0, bankAccountWithAtm.getBalance());
    }
}
