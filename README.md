# manga-rx-interface
AIDL and Model classes to communicate with MangaRx

This is the communication base between MangaRx-App and different Plugins.

They idea is that the main app (MangaRx) search for other apps that provide the "de.mario222k.mangarx.PLUGIN" Intentfilter.
It collects all apps that are installed on the device with this intent filter and offer to connect one of these.

Then the connected app provides Manga-Chpater and Manga-Pages to the main app.

This library provides the models and the AIDL description.

How to use:

Gradle: compile "de.mario222k:manga-rx-interface:0.4"
