package marco.androidweb3jtest.ethwallet;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Created by marco on 09/11/17.
 */

public class EthWalletService {
    private static final EthWalletService ourInstance = new EthWalletService();

    public static EthWalletService getInstance() {
        return ourInstance;
    }

    private static final String ETHEREUM_NODE_URL = "http://jahwa.fr:49405";

    private Web3j web3;

    private EthWalletService() {
        this.web3 = Web3jFactory.build(new HttpService(ETHEREUM_NODE_URL));
    }

    public BigInteger getBalanceAddress(String address) throws Exception {
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance("0x" + address, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            return ethGetBalance.getBalance();
    }
}
