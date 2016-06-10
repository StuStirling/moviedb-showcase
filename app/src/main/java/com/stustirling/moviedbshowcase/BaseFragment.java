/*
 * Created by Stu Stirling on 15 10 2015
 * Copyright (c) 2015 Core Coders Ltd. All Rights Reserved.
 */

package com.stustirling.moviedbshowcase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.stustirling.moviedbshowcase.internal.di.HasComponent;

/**
 * Created by Stu Stirling on 15/10/15.
 */
public class BaseFragment extends Fragment {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true); // TODO: Check to see what this actually does...
    }

   /* *//**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     *//*
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }*/

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }

}
