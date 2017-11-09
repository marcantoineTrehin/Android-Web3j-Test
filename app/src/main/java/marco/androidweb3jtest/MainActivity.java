package marco.androidweb3jtest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.crypto.Wallet;
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
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import marco.androidweb3jtest.ethwallet.EthWallet;
import marco.androidweb3jtest.ethwallet.EthWalletManager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.createNewWalletBtn)
    Button createWalletButton;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.adressEditText)
    EditText addressEditText;

    @BindView(R.id.getBalanceBtn)
    Button balanceBtn;

    @BindView(R.id.balanceTextView)
    TextView balanceTextView;

    private EthWalletManager wManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        wManager = new EthWalletManager(this.getFilesDir().getParentFile());

        createWalletButton.setOnClickListener(createWalletOnClick);
        balanceBtn.setOnClickListener(balanceOnClick);
    }

    View.OnClickListener createWalletOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                EthWallet w = wManager.createWallet("MyPassword");
                textView.setText("Address: " + w.getAddress());
                addressEditText.setText(w.getAddress());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener balanceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                EthWallet w = wManager.getWalletByAddress(addressEditText.getText().toString());
                balanceTextView.setText("Balance: " + w.getBalance().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
