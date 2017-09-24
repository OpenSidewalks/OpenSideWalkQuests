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
import de.westnordost.streetcomplete.quests.bus_stop_bench.AddBusStopBench;
import de.westnordost.streetcomplete.quests.bus_stop_shelter.AddBusStopShelter;
import de.westnordost.streetcomplete.quests.crossing_type.AddCrossingType;
import de.westnordost.streetcomplete.quests.fire_hydrant.AddFireHydrantType;
import de.westnordost.streetcomplete.quests.parking_type.AddParkingType;
import de.westnordost.streetcomplete.quests.passenger_information_display.AddPassengerInformationDisplay;
import de.westnordost.streetcomplete.quests.powerpoles_material.AddPowerPolesMaterial;
import de.westnordost.streetcomplete.quests.orchard_produce.AddOrchardProduce;
import de.westnordost.streetcomplete.quests.recycling.AddRecyclingType;
import de.westnordost.streetcomplete.quests.road_name.data.PutRoadNameSuggestionsHandler;
import de.westnordost.streetcomplete.quests.road_name.data.RoadNameSuggestionsDao;
import de.westnordost.streetcomplete.quests.tactile_paving.AddTactilePavingBusStop;
import de.westnordost.streetcomplete.quests.tactile_paving.AddTactilePavingCrosswalk;
import de.westnordost.streetcomplete.quests.toilet_availability.AddToiletAvailability;
import de.westnordost.streetcomplete.quests.toilets_fee.AddToiletsFee;
import de.westnordost.streetcomplete.quests.housenumber.AddHousenumber;
import de.westnordost.streetcomplete.quests.max_speed.AddMaxSpeed;
import de.westnordost.streetcomplete.quests.opening_hours.AddOpeningHours;
import de.westnordost.streetcomplete.quests.road_name.AddRoadName;
import de.westnordost.streetcomplete.quests.road_surface.AddRoadSurface;
import de.westnordost.streetcomplete.quests.roof_shape.AddRoofShape;
import de.westnordost.streetcomplete.quests.sport.AddSport;
import de.westnordost.streetcomplete.quests.way_lit.AddWayLit;
import de.westnordost.streetcomplete.quests.wheelchair_access.AddWheelChairAccessPublicTransport;
import de.westnordost.streetcomplete.quests.wheelchair_access.AddWheelchairAccessBusiness;

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
