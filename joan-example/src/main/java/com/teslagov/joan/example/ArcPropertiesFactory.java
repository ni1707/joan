package com.teslagov.joan.example;

import com.teslagov.props.CachingPropertySource;
import com.teslagov.props.CascadingPropertySource;
import com.teslagov.props.ClasspathPropertySource;
import com.teslagov.props.PropertyMapReader;
import com.teslagov.props.caching.SlidingWindowCache;
import com.teslagov.props.Properties;

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
