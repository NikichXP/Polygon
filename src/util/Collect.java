package util;

import java.util.Collection;
import java.util.function.Predicate;

public class Collect {
	
	public static <T> T get(Collection<T> set, Predicate<? super T> predicate) {
		return set.stream().filter(predicate).findAny().orElse(null);
	}
}
