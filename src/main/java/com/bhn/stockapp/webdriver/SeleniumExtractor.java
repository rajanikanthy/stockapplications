package com.bhn.stockapp.webdriver;

import java.util.Collection;
import java.util.List;

public interface SeleniumExtractor<T> {
	List<T> extract() throws Exception;
}
