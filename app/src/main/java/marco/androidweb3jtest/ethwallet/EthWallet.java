package marco.androidweb3jtest.ethwallet;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;

/**
 * Created by marco on 09/11/17.
 */

public class EthWallet {

    private EthWalletService ethWalletService;
    private File walletFile;
    private String address;
    private BigInteger balance;

    protected EthWallet(File walletFile, EthWalletService ethWalletService) {
        this.ethWalletService = ethWalletService;
        this.walletFile = walletFile;

        this.load();
    }

    private void load() {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(this.walletFile));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONObject jsonWallet = new JSONObject(sb.toString());

            this.address = jsonWallet.getString("address");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public BigInteger getBalance() throws Exception {
        return this.ethWalletService.getBalanceAddress(this.getAddress());
    }
}
