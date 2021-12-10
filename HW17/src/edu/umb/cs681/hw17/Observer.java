//
// CS681: HW17
// Copyright 2021 Jing Zhang <Jing.Zhang002@umb.edu>
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw17;

@FunctionalInterface
public interface Observer {
	public abstract void update(Observable obs, Object obj);
}
