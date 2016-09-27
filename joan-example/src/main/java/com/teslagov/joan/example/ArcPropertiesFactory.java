package com.teslagov.joan.example;

import com.teslagov.props.*;
import com.teslagov.props.caching.SlidingWindowCache;

import java.time.Clock;
import java.time.Duration;

/**
 * @author Kevin Chen
 */
public class ArcPropertiesFactory {
	public static Properties createArcProperties() {
		return new Properties(
			new CachingPropertySource(
				new SlidingWindowCache<>(
					() -> Clock.systemUTC().instant(),
					Duration.ofSeconds(60)),
				new CascadingPropertySource(
					new ClasspathPropertySource(new PropertyMapReader(), "/secrets.properties"))));
	}
}
