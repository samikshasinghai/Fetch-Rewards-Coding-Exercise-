package coding.excercise;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseExpandableListAdapter
{
    //Initialize variables
    private Context context;
    ArrayList<Integer> listGroup;
    HashMap<Integer,ArrayList<String>> listChild;

    public CustomAdapter(Context context, ArrayList<Integer> listGroup, HashMap<Integer, ArrayList<String>> listChild) {
        this.context = context;
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount()
    {
        //Return group list size
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return listChild.get(listGroup.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View groupView, ViewGroup parent)
    {

        if (groupView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = layoutInflater.inflate(R.layout.list_group_title, null);
        }


        TextView textView = (TextView) groupView
                .findViewById(R.id.textView);

        //Initialize string
        String listID = String.valueOf(getGroup(groupPosition));

        //Set text on TextView
        textView.setText("ListID: " + listID);

        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View childview, ViewGroup parent)
    {
        final String expandedListText = (String) getChild(groupPosition, childPosition);
        if (childview == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childview = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextViewId = (TextView) childview.findViewById(R.id.expandedListItemId);
        TextView expandedListTextViewName = (TextView) childview.findViewById(R.id.expandedListItemName);

        String[] data_split = expandedListText.split("\\|");

        expandedListTextViewId.setText("Item ID: " + data_split[1]);
        expandedListTextViewName.setText("Item Name: " + data_split[0]);
        return childview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
