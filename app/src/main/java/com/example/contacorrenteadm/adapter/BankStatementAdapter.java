package com.example.contacorrenteadm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.model.BankStatement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BankStatementAdapter extends RecyclerView.Adapter<BankStatementAdapter.ViewHolder>{

    private List<BankStatement> bankStatements;

    public BankStatementAdapter(List<BankStatement> bankStatements) {
        this.bankStatements = bankStatements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item_bank_statement, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BankStatement bank = bankStatements.get(position);

        Date dt = new Date();
        try {
          dt = bank.convertDate(bank.dateTransaction);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dtString = formatter.format(dt);

        holder.dateSent.setText(dtString);
        holder.idUserFrom.setText(String.valueOf(bank.id_from));
        holder.idUserTo.setText(String.valueOf(bank.id_to));
        holder.valueSent.setText(String.valueOf(bank.valueTransfer));
    }

    public void replaceData(List<BankStatement> notes) {
        setList(notes);
        notifyDataSetChanged();
    }

    private void setList(List<BankStatement> notes) {
        bankStatements = notes;
    }

    @Override
    public int getItemCount() {
        return bankStatements.size();
    }

    public BankStatement getItem(int position) {
        return bankStatements.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idUserTo;
        public TextView idUserFrom;
        public TextView valueSent;
        public TextView dateSent;

        public ViewHolder(View itemView) {
            super(itemView);
            idUserTo = itemView.findViewById(R.id.id_to);
            idUserFrom = itemView.findViewById(R.id.id_from);
            valueSent = itemView.findViewById(R.id.value_sent);
            dateSent = itemView.findViewById(R.id.date_sent);
        }
    }
}
