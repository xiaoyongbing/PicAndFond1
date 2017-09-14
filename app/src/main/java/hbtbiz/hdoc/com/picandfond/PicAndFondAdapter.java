package hbtbiz.hdoc.com.picandfond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyongbing on 2017/9/13.
 */

public class PicAndFondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_PIC = 0xff01;//图片
    public static final int TYPE_FOND = 0xff02;//文本框
    private List<PicAndFond> picAndFonds = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;//上下文
    private PicAndFondInterface picAndFondInterface;//
    private Boolean flag = true;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_PIC:
                return new PicViewHolder(layoutInflater.inflate(R.layout.item_add_pic,parent,false));
            case TYPE_FOND:
                return new EditViewHolder(layoutInflater.inflate(R.layout.item_add_et,parent,false));
        }
        return null;
    }

    public PicAndFondAdapter(Context c, List<PicAndFond> picAndFonds, PicAndFondInterface picAndFondInterface){
        layoutInflater = LayoutInflater.from(c);
        context = c;
        this.picAndFonds = picAndFonds;
        this.picAndFondInterface = picAndFondInterface;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof PicViewHolder){
            Picasso.with(context).load(picAndFonds.get(position).getPic()).into(((PicViewHolder) holder).imgPic);
            //((PicViewHolder) holder).imgPic.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher));
            if (position == 0){
                ((PicViewHolder) holder).icon_up.setVisibility(View.GONE);
            }else{
                ((PicViewHolder) holder).icon_up.setVisibility(View.VISIBLE);
            }



          /*  if (position != 0){
                ((PicViewHolder) holder).icon_down.setVisibility(View.GONE);
            }else{
                ((PicViewHolder) holder).icon_down.setVisibility(View.VISIBLE);
            }*/

            final PicAndFond bean = picAndFonds.get(position);
            //插入
            ((PicViewHolder) holder).icon_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //bean.setWenzi(holder.edit.getText().toString());
                    //bean.setPic("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1202/09/c3/10378469_1328796216421_1024x1024it.jpg");
                   // indexExChange(picAndFonds,position,position+1);
                    //notifyDataSetChanged();
                    if(picAndFondInterface!=null){
                        picAndFondInterface.insert(position);
                    }

                }
            });

            //上移
            ((PicViewHolder) holder).icon_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //bean.setWenzi(holder.edit.getText().toString());
                    //bean.setPic("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1202/09/c3/10378469_1328796216421_1024x1024it.jpg");

                    indexExChange(picAndFonds, position, position - 1);
                    notifyDataSetChanged();

                }
            });


            ((PicViewHolder) holder).icon_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //picAndFonds.remove(position);
                    //notifyDataSetChanged();
                    if(picAndFondInterface!=null){
                        picAndFondInterface.delete(position,0);
                    }
                }
            });

        }else if(holder instanceof  EditViewHolder){
            ((EditViewHolder) holder).editText.setText(picAndFonds.get(position).getWenzi());
            ((EditViewHolder) holder).editText.setTag(position);
            //是否接触edit
            ((EditViewHolder) holder).editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    flag = true;
                    return false;
                }
            });
            ((EditViewHolder) holder).editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(flag){
                        if(position == (int) ((EditViewHolder) holder).editText.getTag()){
                            picAndFonds.get(position).setWenzi(s.toString());
                        }
                    }

                }
            });
            if (position == 0){
                ((EditViewHolder) holder).icon_up.setVisibility(View.GONE);
            }else{
                ((EditViewHolder) holder).icon_up.setVisibility(View.VISIBLE);
            }



            /*if (position != 0){
                ((EditViewHolder) holder).icon_down.setVisibility(View.GONE);
            }else{
                ((EditViewHolder) holder).icon_down.setVisibility(View.VISIBLE);
            }*/

            final PicAndFond bean = picAndFonds.get(position);
            //新增
            ((EditViewHolder) holder).icon_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //bean.setWenzi(holder.edit.getText().toString());
                    //bean.setPic("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1202/09/c3/10378469_1328796216421_1024x1024it.jpg");
                    //indexExChange(picAndFonds,position,position+1);
                    //notifyDataSetChanged();
                    if(picAndFondInterface!=null){
                        picAndFondInterface.insert(position);
                    }

                }
            });

            //上移
            ((EditViewHolder) holder).icon_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bean.setWenzi( ((EditViewHolder) holder).editText.getText().toString());
                    //bean.setPic("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1202/09/c3/10378469_1328796216421_1024x1024it.jpg");
                    //picAndFonds.set(position-1,bean);
                    indexExChange(picAndFonds, position, position - 1);
                    flag = false;
                    notifyDataSetChanged();

                }
            });


            ((EditViewHolder) holder).icon_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(picAndFondInterface!=null){
                        picAndFondInterface.delete(position,1);
                    }
                    /*picAndFonds.remove(position);
                    notifyDataSetChanged();*/
                }
            });
        }
    }

    public static <T> List<T> indexExChange(List<T> list, int index1, int index2){
        T t = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, t);
        return list;
    }

    @Override
    public int getItemCount() {
        return picAndFonds==null?0:picAndFonds.size();
    }

    @Override
    public int getItemViewType(int position) {
        return picAndFonds.get(position).getType() == 0 ? TYPE_PIC : TYPE_FOND ;
    }

    /**
     * 图片的item
     */
    public static class PicViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgPic;
        private ImageView icon_up;//上移
        private ImageView icon_down;//下移
        private ImageView icon_delete;//删除
        public PicViewHolder(View v){
            super(v);
            imgPic = (ImageView)v.findViewById(R.id.img_add);
            icon_up = (ImageView) v.findViewById(R.id.icon_up);
            icon_down = (ImageView) v.findViewById(R.id.icon_down);
            icon_delete = (ImageView) v.findViewById(R.id.icon_delete);
        }
    }

    /**
     * 文字的item
     */
    public static class EditViewHolder extends RecyclerView.ViewHolder {

        public MyEditText editText;
        private ImageView icon_up;//上移
        private ImageView icon_down;//下移
        private ImageView icon_delete;//删除

        public EditViewHolder(View v) {
            super(v);
            editText = (MyEditText) v.findViewById(R.id.et_add);
            icon_up = (ImageView) v.findViewById(R.id.icon_up);
            icon_down = (ImageView) v.findViewById(R.id.icon_down);
            icon_delete = (ImageView) v.findViewById(R.id.icon_delete);
        }
    }

}
