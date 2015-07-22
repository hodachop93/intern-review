package intership.dev.contact.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import intership.dev.contact.R;
import intership.dev.contact.model.User;

/**
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactListAdapter extends BaseAdapter implements ContactDialog.OnClickContactDialog,
        DialogInterface.OnDismissListener {
    private Context mContext;
    private List<User> mUsers;
    private ContactDialog dialog;

    //Position of an item in ListView when it was clicked
    private int mPosition;

    /**
     * Create an adapter for a ListView that contains a list of contacts
     *
     * @param mContext The context that adapter is running
     * @param mUsers   The collection of users
     */
    public ContactListAdapter(Context mContext, List<User> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
        dialog = new ContactDialog(mContext);
        dialog.setOnClickListViewContactListener(this);
        dialog.setOnDismissListener(this);
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_contacts, parent, false);

            holder = new ViewHolder();
            holder.mAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.mUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            holder.mImgBtnEdit = (ImageButton) convertView.findViewById(R.id.imgBtnEdit);
            holder.mImgBtnDelete = (ImageButton) convertView.findViewById(R.id.imgBtnDelete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        setValue(holder, position);
        setEvent(holder, position);
        return convertView;
    }

    /**
     * Set events for a ViewHolder corresponding to a user in list contacts
     *
     * @param holder   The viewholder in the current convert view
     * @param position The position of a user in list of users corresponding
     *                 to the position of an item in ListView
     */
    private void setEvent(final ViewHolder holder, final int position) {
        final User user = (User) getItem(position);

        holder.mImgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mImgBtnEdit.isSelected()) {
                    holder.mImgBtnEdit.setSelected(false);
                } else {
                    holder.mImgBtnEdit.setSelected(true);
                }


            }
        });


        holder.mImgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setIsDelete(true);
                holder.mImgBtnDelete.setSelected(true);
                mPosition = position;
                dialog.show();
            }
        });
    }


    /**
     * Set value for a ViewHolder corresponding to a user in list contacts
     *
     * @param holder   The viewholder in the current convert view
     * @param position The position of a user in list of users corresponding
     *                 to the position of an item in ListView
     */
    private void setValue(ViewHolder holder, int position) {
        User user = (User) getItem(position);

        holder.mAvatar.setImageBitmap(user.getAvatar());
        holder.mUsername.setText(user.getUserName());
        if (user.isDelete()) {
            holder.mImgBtnDelete.setSelected(true);
        } else {
            holder.mImgBtnDelete.setSelected(false);
        }
    }

    @Override
    public void onClickBtnOK(View v) {
        mUsers.remove(mPosition);
        notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void onClickBtnCancel(View v) {
        dialog.dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        mUsers.get(mPosition).setIsDelete(false);
        notifyDataSetChanged();
    }


    /**
     * This class is used to control view items in convertview
     */
    private static class ViewHolder {
        ImageView mAvatar;
        TextView mUsername;
        ImageButton mImgBtnEdit;
        ImageButton mImgBtnDelete;
    }
}
