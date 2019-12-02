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

import java.util.List;

public class BankStatementAdapter extends RecyclerView.Adapter<BankStatementAdapter.ViewHolder> {

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
        holder.idUserTo.setText(bank.id_to);
        holder.valueSent.setText((int) bank.valueTransfer);
        holder.dateSent.setText((CharSequence) bank.dateTransaction);
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
        public TextView valueSent;
        public TextView dateSent;

        public ViewHolder(View itemView) {
            super(itemView);

            idUserTo = itemView.findViewById(R.id.name_client_sent);
            valueSent = itemView.findViewById(R.id.value_sent);
            dateSent = itemView.findViewById(R.id.date_sent);
        }
    }
}
