# Android App to count your Tridom score

CircleCi build
[![CircleCI](https://circleci.com/gh/cloudsftp/TridomCounter/tree/develop.svg?style=svg)](https://circleci.com/gh/cloudsftp/TridomCounter/tree/develop)

This app is written in **Kotlin** using **Android Studio**.\
Source control is managed by **Gitflow**.

### Gitflow

#### Configuration

`git flow init`

Main Branches:

- production releases: **master**
- "next release" development: **develop**

Branch prefixes:

- Feature: **feature/**
- Bugfix: **bugfix/**
- Release: **release/**
- Hotfix: **hotfix/**
- Support: **support/**
- Version tag prefix: **TC.**

Hooks and Filters directory can be default

#### Usage

- Features:
  - start feature branch: `git flow feature start [name]`
  - end feature branch: `git flow feature finish [name]`
- Releases:
  - start release branch: `git flow release start [XX].[XX]`
  - end release branch: `git flow release finish [XX].[XX]`
