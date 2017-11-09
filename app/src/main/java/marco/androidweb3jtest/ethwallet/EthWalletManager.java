package marco.androidweb3jtest.ethwallet;

import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 09/11/17.
 */

public class EthWalletManager {

    private File directory;
    private List<EthWallet> wallets = new ArrayList<>();

    public EthWalletManager(File appDirectory) {
        this.directory = appDirectory;
    }

    public EthWallet createWallet(String password) throws Exception {
        String walletFileName = WalletUtils.generateLightNewWalletFile(password, this.directory);
        Log.d("EthWalletManager", "Wallet file: " + walletFileName);

        EthWallet wallet = new EthWallet(
                new File(directory.getAbsolutePath() + "/" + walletFileName),
                EthWalletService.getInstance());

        this.wallets.add(wallet);
        return wallet;
    }

    public EthWallet getWalletByAddress(String address) throws Exception {
        for (EthWallet w : wallets) {
            Log.i("MYYY", "Current wallet: " + w.getAddress());
            if (address.equals(w.getAddress()))
                return w;
        }

        throw new Exception("Wallet not found: " + address);
    }
}
