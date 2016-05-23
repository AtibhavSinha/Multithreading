package com.ati;

import java.util.ArrayList;
import java.util.List;

public class DeadLockTree {

	static class TreeNode {

		TreeNode parent = null;
		List<TreeNode> children = new ArrayList<TreeNode>();

		public synchronized void addChild(TreeNode child) {
			if (!this.children.contains(child)) {
				this.children.add(child);
				child.setParentOnly(this);
			}
		}

		public synchronized void addChildOnly(TreeNode child) {
			if (!this.children.contains(child)) {
				this.children.add(child);
			}
		}

		public synchronized void setParent(TreeNode parent) {
			this.parent = parent;
			parent.addChildOnly(this);
		}

		public synchronized void setParentOnly(TreeNode parent) {
			this.parent = parent;
		}
	}

	public static void main(String[] args) {
		final TreeNode child = new TreeNode();
		final TreeNode parent = new TreeNode();

		// First thread 1 calls parent.addChild(child). Since addChild() is synchronized thread 1
		// effectively locks the parent object for access from other treads.
		new Thread(new Runnable() {
			public void run() {
				parent.addChild(child);
			}
		}).start();

		// Then thread 2 calls child.setParent(parent). Since setParent() is synchronized thread 2
		// effectively locks the child object for access from other threads.
		new Thread(new Runnable() {
			public void run() {
				child.setParent(parent);
			}
		}).start();

		// Now both child and parent objects are locked by two different threads. Next thread 1
		// tries to call child.setParentOnly() method, but the child object is locked by thread 2,
		// so the method call just blocks. Thread 2 also tries to call parent.addChildOnly() but the
		// parent object is locked by thread 1, causing thread 2 to block on that method call. Now
		// both threads are blocked waiting to obtain locks the other thread holds.
	}

}
