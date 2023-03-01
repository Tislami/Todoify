package com.tis.todoify.domain.model

import NoteModel

data class FolderModel(
    val name: String = "",
    val noteList: List<NoteModel> = emptyList()
)


val defaultFolderModel = FolderModel(
    name = "My Folder",
    noteList = listOf(
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
        NoteModel(),
    )
)


val defaultNoteList = listOf(
    NoteModel("Title", "dlmflmlf dfds dsfsdf dfasma lfmlf s"),
    NoteModel("Ti", "dlmflmlf malfmlfs"),
    NoteModel("Tit", "dlmflmlf msad dkafmksmfakmfkfamsa askdfmskdmfsoakmfakosmf askofmsdkmfsokma asokfmsafasdfskm adskfmdskfmsfkmsdf adkomfkdsmfs oadkmfasokdmfoksm dsoafmokfmasko adofmsm da dkfd afsfsadmasoksma aodkmfskodfsdf mffkmsdkf aasdfkms lfmlfs"),
    NoteModel("Titl edfsd", "dlmflm dsf df asf fs saf lfmalfmlfs"),
    NoteModel("Tit", "1232412 sdafsd 4dfsda "),
    NoteModel("Titdaf sle", "dlmflmd dfds lfdsafdf adfa sdfafsmalfmlfs"),
    NoteModel("Titl essa", "dlmfl   mlfmaldsads adfdffmlfs"),
    NoteModel("Titlesada dfsd", "dlmflma  dfsfs  dfsadlfmalfmlfs"),
    NoteModel("Title", "dlmflm lfmalfms afslfs"),
    NoteModel("Title 123 dsafs", "dlmflmlfsafs sadffs dasfkmfd akdfmkmf asdkfmskfmas asdmfsmaosm sdafkmfkms askfmskmfakm adofkmasfkm adsfmadmfao adsokfmkms asfmsomfsmalfmlfs"),
    NoteModel("123 trrfd", "dlm sdfaasf asdfsdfas asdfsdfskmkmasdofms asfsfsnmomaofd flmlfmdsfsa asdfsfsalfmlfs"),
    NoteModel("Ti gh tle", "dlmflmlsdafsdfkm adsmfskomfasodk adafmksdmfkdsm asdmfksomfasok asokfmskmfmalfmlfs"),
)