package com.msk.produtosperigosos.info;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msk.produtosperigosos.R;

public class SobreApp extends Fragment {

    private View tela;
    private PackageInfo pinfo;
    private TextView versao;
    private String nr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tela = inflater.inflate(R.layout.sobre, container, false);

        versao = (TextView) tela.findViewById(R.id.tvVersaoApp);

        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        nr = pinfo.versionName;

        versao.setText(getResources().getString(R.string.desenvolvedor, nr));

        return tela;
    }


}
