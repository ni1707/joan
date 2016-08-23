package com.teslagov.joan;

import com.teslagov.properties.CachingPropertySource;
import com.teslagov.properties.CascadingPropertySource;
import com.teslagov.properties.ClasspathPropertySource;
import com.teslagov.properties.Properties;
import com.teslagov.properties.PropertyMapReader;
import com.teslagov.properties.caching.SlidingWindowCache;

import java.time.Clock;
import java.time.Duration;

/**
 * @author Kevin Chen
 */
public class PropertiesFactory
{
	public static Properties createProperties()
	{
		return new Properties(
			new CachingPropertySource(
				new SlidingWindowCache<>(
					() -> Clock.systemUTC().instant(),
					Duration.ofSeconds( 60 ) ),
				new CascadingPropertySource(
					new ClasspathPropertySource( new PropertyMapReader(), "/secrets.properties" ) ) ) );
	}
}
