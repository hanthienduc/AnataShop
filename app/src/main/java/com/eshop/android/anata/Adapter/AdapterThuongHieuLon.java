package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.TrangDanhMucSanPham.SanPhamTheoDanhMucFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Han on 16/09/2016.
 */
public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolderThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieus;
    boolean kiemtra;

    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus, boolean Kiemtra) {
        this.context = context;
        this.thuongHieus = thuongHieus;
        kiemtra = Kiemtra;
    }


    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {

        TextView txtTieuDeThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolderThuongHieu(View itemView) {
            super(itemView);
            txtTieuDeThuongHieu = (TextView) itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imgHinhThuongHieu = (ImageView) itemView.findViewById(R.id.imgHinhThuongHieuLonDienTu);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressDownload);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnThuongHieuLon);

        }
    }

    @Override
    public AdapterThuongHieuLon.ViewHolderThuongHieu onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_thuonghieulon, parent, false);

        ViewHolderThuongHieu viewHolder = new ViewHolderThuongHieu(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterThuongHieuLon.ViewHolderThuongHieu holder, final int position) {
        final ThuongHieu thuongHieu = thuongHieus.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTENTH());

        Picasso.with(context).load(thuongHieus.get(position).getHINHTH()).resize(120, 120).into(holder.imgHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SanPhamTheoDanhMucFragment sanPhamTheoDanhMucFragment = new SanPhamTheoDanhMucFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("MALOAITH", thuongHieu.getMATH());
                bundle.putBoolean("KIEMTRA", kiemtra);
                bundle.putString("TENLOAITH", thuongHieu.getTENTH());

                sanPhamTheoDanhMucFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.replace(R.id.themFragment,sanPhamTheoDanhMucFragment);
                fragmentTransaction.commit();
                // Log.d("Click", thuongHieu.getMATH() + " - " + thuongHieu.getTENTH());
            }
        });


    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }


}
