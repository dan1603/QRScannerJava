package com.kalashnyk.denys.qrscanner.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//наша кастомная Скопа
@Scope
@Retention(RetentionPolicy.CLASS)// какоя-то политика памяти ??
public @interface ViewModelScope {
}
