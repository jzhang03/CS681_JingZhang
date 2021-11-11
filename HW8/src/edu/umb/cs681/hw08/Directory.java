//
// CS681: HW8
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement implements Runnable{
	private LinkedList<FSElement> children =  new LinkedList<FSElement>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent, name, size, creationTime);
    } 
    
    public LinkedList<FSElement>getChildren() {
    	lock.lock();
    	try {
    		return this.children;
    	} finally {
    		lock.unlock();
    	}
    }

    public void appendChild(FSElement child) {
    	lock.lock();
    	try {
    		this.children.add(child);
            child.setParent(this);
    	} finally {
    		lock.unlock();
    	}
    }

    public int countChildren() {
    	lock.lock();
    	try {
    		return this.children.size();
    	} finally {
    		lock.unlock();
    	}
    }
    
    public LinkedList<Directory> getSubDirectories() {
    	LinkedList<Directory> subDirectories = new LinkedList<Directory>();
    	lock.lock();
    	try {
    		for(FSElement file: children) {
                if(file.isDirectory())
                    subDirectories.add((Directory) file);
            }
            return subDirectories;
    	} finally {
    		lock.unlock();
    	}
    }

    public LinkedList<File> getFiles() {
    	LinkedList<File> files = new LinkedList<File>();
    	lock.lock();
    	try {
    		for(FSElement file: children) {
                if(file.isFile())
                    files.add((File) file);
            }
            return files;
    	} finally {
    		lock.unlock();
    	}
    }

    public int getTotalSize() {
    	int totalSize = 0;
    	lock.lock();
    	try {
            for(FSElement file: children) {
                if(file.isDirectory()) {
                    totalSize += ((Directory) file).getTotalSize();
                } else {
                    totalSize += file.getSize();
                }
            }
            return totalSize;
    	} finally {
    		lock.unlock();
    	}
    }

    public boolean isDirectory() {
    	lock.lock();
    	try {
    		return true;
    	} finally {
    		lock.unlock();
    	}
    }

    public boolean isFile() {
    	lock.lock();
    	try {
    		return false;
    	} finally {
    		lock.unlock();
    	}
    }
    
    public boolean isLink() {
    	lock.lock();
    	try {
    		return false;
    	} finally {
    		lock.unlock();
    	}
    }
    
    @Override
	public void run() {
		try {
			System.out.println("Running Thread: " + Thread.currentThread().getId());
		} catch(Exception e){
			System.out.println("Exception caught");
		}
	}
}
