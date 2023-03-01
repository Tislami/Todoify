package com.tis.todoify.domain.model

data class FolderModel(
    val name: String = "",
    val noteList: List<Note> = emptyList()
)


val defaultFolderModel = FolderModel(
    name = "My Folder",
    noteList = listOf(
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
        Note(),
    )
)


val defaultNoteList = listOf(
    Note("Title", "dlmflmlf dfds dsfsdf dfasma lfmlf s"),
    Note("Ti", "dlmflmlf malfmlfs"),
    Note("Tit", "dlmflmlf msad dkafmksmfakmfkfamsa askdfmskdmfsoakmfakosmf askofmsdkmfsokma asokfmsafasdfskm adskfmdskfmsfkmsdf adkomfkdsmfs oadkmfasokdmfoksm dsoafmokfmasko adofmsm da dkfd afsfsadmasoksma aodkmfskodfsdf mffkmsdkf aasdfkms lfmlfs"),
    Note("Titl edfsd", "dlmflm dsf df asf fs saf lfmalfmlfs"),
    Note("Tit", "1232412 sdafsd 4dfsda "),
    Note("Titdaf sle", "dlmflmd dfds lfdsafdf adfa sdfafsmalfmlfs"),
    Note("Titl essa", "dlmfl   mlfmaldsads adfdffmlfs"),
    Note("Titlesada dfsd", "dlmflma  dfsfs  dfsadlfmalfmlfs"),
    Note("Title", "dlmflm lfmalfms afslfs"),
    Note("Title 123 dsafs", "dlmflmlfsafs sadffs dasfkmfd akdfmkmf asdkfmskfmas asdmfsmaosm sdafkmfkms askfmskmfakm adofkmasfkm adsfmadmfao adsokfmkms asfmsomfsmalfmlfs"),
    Note("123 trrfd", "dlm sdfaasf asdfsdfas asdfsdfskmkmasdofms asfsfsnmomaofd flmlfmdsfsa asdfsfsalfmlfs"),
    Note("Ti gh tle", "dlmflmlsdafsdfkm adsmfskomfasodk adafmksdmfkdsm asdmfksomfasok asokfmskmfmalfmlfs"),
)