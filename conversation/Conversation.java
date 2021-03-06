package conversation;

import support.Property;
import support.GeneralProperty;
import support.PropertyProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conversation implements Serializable, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(GeneralProperty.CONVERSATION);
    }

    protected List<Conversation> conversationTree = new ArrayList<>();
    protected String Title;
    protected int layerNumber = 0;
    protected boolean visible = true;
    protected int branchNumber;

    public List<Conversation> getConversationTree() {
        return conversationTree;
    }

    public void addConversationBranch(Conversation conversation, int branchNumber){
        branchNumber --;
        conversation.setLayerNumber(layerNumber+1);
        if(branchNumber < conversationTree.size()){
            conversationTree.get(branchNumber).getConversationTree().add(conversation);
        } else {
            conversationTree.add(conversation);
        }
    }

    public void run() {}

    public String getTitle() {
        return Title;
    }

    public Conversation setTitle(String title) {
        Title = title;
        return this;
    }

    public Conversation setIsVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean getIsVisible(){
        return visible;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public Conversation setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
        return this;
    }

    public int getLayerNumber() {
        return layerNumber;
    }

    public Conversation setLayerNumber(int layerNumber) {
        this.layerNumber = layerNumber;
        return this;
    }
}
