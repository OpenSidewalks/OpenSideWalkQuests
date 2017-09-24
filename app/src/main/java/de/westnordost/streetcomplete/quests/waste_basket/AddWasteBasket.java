package de.westnordost.streetcomplete.quests.waste_basket;

import android.os.Bundle;

import java.util.Map;

import javax.inject.Inject;

import de.westnordost.streetcomplete.R;
import de.westnordost.streetcomplete.data.osm.SimpleOverpassQuestType;
import de.westnordost.streetcomplete.data.osm.changes.StringMapChangesBuilder;
import de.westnordost.streetcomplete.data.osm.download.OverpassMapDataDao;
import de.westnordost.streetcomplete.quests.AbstractQuestAnswerFragment;
import de.westnordost.streetcomplete.quests.YesNoQuestAnswerFragment;

public class AddWasteBasket extends SimpleOverpassQuestType
{
    @Inject public AddWasteBasket(OverpassMapDataDao overpassServer) { super(overpassServer); }

    @Override protected String getTagFilters()
    {
        return "nodes with (public_transport=platform or (highway=bus_stop and public_transport!=stop_position)) and !waste_basket";
    }

    public AbstractQuestAnswerFragment createForm()
    {
        return new YesNoQuestAnswerFragment();
    }

    public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
    {
        String yesno = answer.getBoolean(YesNoQuestAnswerFragment.ANSWER) ? "yes" : "no";
        changes.add("waste_basket", yesno);
    }

    @Override public String getCommitMessage() { return "Add bus stop waste basket"; }
    @Override public int getIcon() { return R.drawable.ic_quest_bus; }
    @Override public int getTitle(Map<String, String> tags)
    {
        boolean hasName = tags.containsKey("name");
        if(hasName) return R.string.quest_wasteBasket_name_title;
        else        return R.string.quest_wasteBasket_title;
    }
}
