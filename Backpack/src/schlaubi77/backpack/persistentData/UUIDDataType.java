package schlaubi77.backpack.persistentData;

import java.util.UUID;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class UUIDDataType implements PersistentDataType<long[], UUID> {
	
	// MSB | LSB

	@Override
	public Class<UUID> getComplexType() {
		return UUID.class;
	}

	@Override
	public Class<long[]> getPrimitiveType() {
		return long[].class;
	}
	
	@Override
	public UUID fromPrimitive(long[] primitive, PersistentDataAdapterContext arg) {
		
		return new UUID(primitive[0], primitive[1]);
	}

	@Override
	public long[] toPrimitive(UUID complex, PersistentDataAdapterContext arg) {
		long[] primitive = {complex.getMostSignificantBits(), complex.getLeastSignificantBits()};
		return primitive;
	}

}
