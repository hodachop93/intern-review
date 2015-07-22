package intership.dev.contact.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import intership.dev.contact.R;
import intership.dev.contact.model.User;

/**
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactListAdapter extends BaseAdapter {
    private Context mContext;
    private List<User> mUsers;
    private ContactDialog dialog;
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
        User user = (User) getItem(position);

        holder.mImgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mImgBtnEdit.isSelected()) {
                    holder.mImgBtnEdit.setSelected(false);
                } else {
                    holder.mImgBtnEdit.setSelected(true);
                }
                //Toast.makeText(mContext, "edit" + position + "", Toast.LENGTH_SHORT).show();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(R.layout.dialog_contact);
                builder.create().show();*/

                dialog.show();

            }
        });


        holder.mImgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mImgBtnDelete.isSelected()) {
                    holder.mImgBtnDelete.setSelected(false);
                } else {
                    holder.mImgBtnDelete.setSelected(true);
                }
                Toast.makeText(mContext, "delete" + position + "", Toast.LENGTH_SHORT).show();
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
