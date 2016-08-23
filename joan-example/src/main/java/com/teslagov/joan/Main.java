package com.teslagov.joan;

/**
 * @author Kevin Chen
 */
public class Main
{
	public static void main( String[] args )
	{
		ArcConfiguration arcConfiguration = null;

		TokenFetcher tokenFetcher = new TokenFetcher();
		Token token = tokenFetcher.fetchToken( arcConfiguration );
	}
}
