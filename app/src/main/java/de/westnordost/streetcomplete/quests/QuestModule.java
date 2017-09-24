package de.westnordost.streetcomplete.quests;

import java.util.Arrays;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.westnordost.streetcomplete.data.QuestType;
import de.westnordost.streetcomplete.data.QuestTypes;
import de.westnordost.streetcomplete.data.osm.download.OverpassMapDataDao;
import de.westnordost.streetcomplete.data.osmnotes.OsmNoteQuestType;
import de.westnordost.streetcomplete.quests.baby_changing_table.AddBabyChangingTable;
import de.westnordost.streetcomplete.quests.bike_parking_capacity.AddBikeParkingCapacity;
import de.westnordost.streetcomplete.quests.bike_parking_cover.AddBikeParkingCover;
import de.westnordost.streetcomplete.quests.building_levels.AddBuildingLevels;
import de.westnordost.streetcomplete.quests.bus_stop_lit.AddBusStopLit;

import de.westnordost.streetcomplete.quests.bus_stop_bench.AddBusStopBench;
import de.westnordost.streetcomplete.quests.bus_stop_shelter.AddBusStopShelter;
import de.westnordost.streetcomplete.quests.passenger_information_display.AddPassengerInformationDisplay;
import de.westnordost.streetcomplete.quests.road_name.data.PutRoadNameSuggestionsHandler;
import de.westnordost.streetcomplete.quests.road_name.data.RoadNameSuggestionsDao;
import de.westnordost.streetcomplete.quests.tactile_paving.AddTactilePavingBusStop;
import de.westnordost.streetcomplete.quests.bin.AddBin;

@Module
public class QuestModule
{
	@Provides @Singleton public static QuestTypes questTypeList(
			OsmNoteQuestType osmNoteQuestType, OverpassMapDataDao o,
			RoadNameSuggestionsDao roadNameSuggestionsDao,
			PutRoadNameSuggestionsHandler putRoadNameSuggestionsHandler)
	{
		QuestType[] questTypesOrderedByImportance = {
				// ↓ notes
				osmNoteQuestType,

				new AddBin(o),
				new AddBusStopLit(o),
				new AddPassengerInformationDisplay(o),
				new AddBusStopBench(o),
				new AddBusStopShelter(o),
				new AddTactilePavingBusStop(o)
		};

		return new QuestTypes(Arrays.asList(questTypesOrderedByImportance));
	}

	@Provides @Singleton public static OsmNoteQuestType osmNoteQuestType()
	{
		return new OsmNoteQuestType();
	}
}
