package Conversations;

import java.util.ArrayList;

public class Conversation {
    protected ArrayList<Conversation> conversationTree = new ArrayList<Conversation>();
    protected String Title;

    public ArrayList<Conversation> getConversationTree() {
        return conversationTree;
    }

    public void addConversationBranch(Conversation conversation){
        conversationTree.add(conversation);
    }

    public void run(){}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}