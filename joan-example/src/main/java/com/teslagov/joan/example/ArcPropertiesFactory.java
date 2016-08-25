package com.teslagov.joan.example;

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
public class ArcPropertiesFactory
{
	public static Properties createArcProperties()
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
