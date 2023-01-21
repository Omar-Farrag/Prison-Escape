package Music;

public enum Track {

    // Audio files must be in wav format. Apparently mp3 needs licensing
    SONGNAME("FOLDER_NAME\\FULL TEXT NAME OF AUDIO FILE WITH EXTENSION"),

    NOTHING(""),
    STATE_OF_MIND("Songs\\Nas - N.Y. State of Mind (Official Audio).wav", "NY State of Mind", "NAS"),
    BILLIE_JEAN("Songs\\Michael Jackson - Billie Jean (Official Video).wav", "Billie Jean", "Michael Jackson"),
    STAYIN_ALIVE("Songs\\Bee Gees - Stayin' Alive (Official Music Video).wav", "Stayin' Alive", "Bee Gees"),
    BABY_I_LOVE_YOUR_WAY("Songs\\Big Mountain - Baby I Love Your Way(1994).wav", "Baby I Love Your Way",
            "Big Mountain"),
    WE_WILL_ROCK_YOU("Songs\\Queen - We Will Rock You [Lyrics].wav", "We Will Rock You", "Queen"),
    WAKA_WAKA("Songs\\Shakira - Waka Waka (This Time For Africa) (Official HD Video) ft. Freshlyground.wav",
            "WAKA WAKA", "Shakira"),
    IT_WAS_A_GOOD_DAY("Songs\\Ice Cube - It Was A Good Day (Official Music Video).wav", "It Was A Good Day",
            "Ice Cube"),
    YOU_KNOW_HOW_WE_DO_IT("Songs\\You Know How We Do It.wav", "You Know How We Do It", "Ice Cube"),
    CREAM("Songs\\Wu-Tang Clan - C.R.E.A.M. (Official HD Video).wav", "C.R.E.A.M", "Wu-Tang Clan"),
    IT_AINT_HARD_TO_TELL("Songs\\Nas - It Ain't Hard to Tell (Official HD Video).wav", "It Ain't Hard to Tell", "NAS"),
    SHOOK_ONES("Songs\\Mobb Deep - Shook Ones, Pt. II (Official Audio).wav", "Shook Ones", "Mobb Deep"),
    HARMONICA("Songs\\Harmonica Blues Solo.wav"),
    DARLIN("Songs\\Darlin_-The-Beach-Boys-_Lyrics_.wav", "Darlin'", "The Beach Boys"),

    PUNCH("Effects\\Y2Mate.is - Punch sound effect-wyScSXX3x3E-160k-1657845792593.wav"),
    DODGE("Effects\\Y2Mate.is - Whoosh Sound Effect HD  No Copyright-D9AxvFHq9yU-160k-1655596282460.wav"),
    BRAWL("Effects\\Riot Sound, Angry Crowd sound effects.wav"),
    VICTORY("Effects\\Victory Sound Effect - No Copyright (HD)-YoutubeConvert.cc.wav"),
    DEATH_SOUND("Effects\\gta wasted-sound effect.wav"),

    GETTING_BEATEN(
            "Effects\\Y2Mate.is - Getting beaten up sound effect no copyright-3ltIlF5h_U4-160k-1657855831919.wav"),
    FEW_MOMENTS_LATER("Effects\\A FEW MOMENTS LATER HD spongebob sound effect 1-YoutubeConvert.cc.wav"),

    DENIED("Effects\\Denied - Gaming Sound Effect (HD)-YoutubeConvert.cc.wav"),
    SIREN("Effects\\Police Siren Sound Effect SFX for Filmmaking FREE - NO COPYRIGHT-YoutubeConvert.cc.wav"),
    KNOCK("Effects\\Knock knock (sound effect)-YoutubeConvert.cc.wav"),
    SHH("Effects\\Shhh - Sound Effect-YoutubeConvert.cc.wav"),
    SWITCH("Effects\\Switch On Sound Effect.wav"),

    ADTHAN("Effects\\Adhan (Call to prayer) ｜ Mishary Rashid Alafasy ｜ Fajr ｜ Maqam Hijaz ᴴᴰ-[AudioTrimmer.com].wav"),
    CONFETTI("Effects\\Confetti.wav"),
    TASBEEH("Effects\\SOBHAN ALLAH.wav"),
    TAKBEER("Effects\\ALLAHU AKBAR.wav"),
    TAHMEED("Effects\\AL HAMDULELAH.wav"),

    CELL_TRACK("Effects\\Prison Cell Door sound effect-YoutubeConvert.cc.wav"),
    KITCHEN("Effects\\Kitchen ambience -1 Hour  Cooking Sounds (1).wav"),
    OPEN_YARD("Effects\\Open yard.wav"),

    HammeringNail("Effects\\HammeringNail.wav"),
    HandSawEffect("Effects\\HandSaw.wav"),
    GlassBreak("Effects\\GlassBreak.wav"),

    WATER_DISPENSER("Effects\\fridge water dispenser - sound effect.wav"),
    KETTLE_HEATING("Effects\\Electric kettle boiling sound effect ｜ High Quality sound-[AudioTrimmer.com].wav"),
    SKIN_BURN("Effects\\Burn Skin (Horror) - Sound Effects (HD).wav"),
    MATCH_LIT("Effects\\Match lighting Sound Effect.wav"),
    TEA("Effects\\Tea pouring - Sound Effect (SFX)-[AudioTrimmer.com].wav"),

    COUGH("Effects\\Man Coughing Sound Effect-[AudioTrimmer.com].wav"),
    AK47("Effects\\AK-47 sound FX.wav"),
    HANDGUN("Effects\\Pistol Sound Effects 9mm.wav"),
    M16("Effects\\M16 Sound Effect.wav"),
    SNIPER_RIFLE("Effects\\Sniper Rifle Sound Effect (High Quality & Single Shot).wav"),
    RPG("Effects\\RPG sound effect.wav"),
    GRENADE("Effects\\pubg mobile grenade sounds op.wav"),
    SMOKE_BOMB("Effects\\Smoke Bomb ｜ Free Sound Effect.wav"),

    GUARDS_FALLING("Effects\\Heavy object Hit and body thud sound effect.wav"),

    DRIBBLE("Effects\\Basketball Bounce Sound (1).wav"),
    BASKETBALL_HOOP("Effects\\Basketball Swish Sound Effect.wav"),
    INMATES_LAUGHING("Effects\\Goanimate crowd laughing sound effect (MOST VIEWED VIDEO).wav"),
    CHEERS("Effects\\Cheering.wav"),
    BASKETBALL_RIM("Effects\\BASKETBALL HITTING RIM - Gaming Sound Effects HD FREE NO Copyright.wav"),
    BOOS("Effects\\Crowd Booing Sound Effects-[AudioTrimmer.com].wav"),
    REP("Effects\\dumbbell sound effect weight lifting-[AudioTrimmer.com].wav"),

    SHORT_BRAWL("Effects\\ShortBrawl.wav"),
    INMATE_COUNT("Effects\\InmateCount-[AudioTrimmer.com].wav"),
    SNORING("Effects\\Snoring-[AudioTrimmer.com].wav"),
    WAITING("Effects\\Waiting-[AudioTrimmer.com].wav"),
    UNLOCK_CELL("Effects\\OpenPrisonCell-[AudioTrimmer.com].wav"),
    FOOT_STEPS("Effects\\Footsteps-[AudioTrimmer.com].wav"),
    RUNNING("Effects\\Running-[AudioTrimmer.com].wav"),
    EXPLOSION("Effects\\TimeBombExplosion.wav"),
    ALARM("Effects\\PrisonSiren-[AudioTrimmer.com].wav"),
    ROPE_THROWING("Effects\\RopeThrow.wav"),
    CLIMBING("Effects\\Climbing.wav"),
    OPEN_DOOR("Effects\\DoorOpening-[AudioTrimmer.com].wav"),
    PANIC("Effects\\Panic.wav"),
    GANGSTER_PARADISE("Effects\\GangstasParadise-[AudioTrimmer.com].wav");

    String trackName;
    String songName;
    String artist;

    Track(String s) {
        trackName = s;
        songName = "";
        artist = "";
    }

    Track(String s, String songName, String artist) {
        trackName = s;
        this.songName = songName;
        this.artist = artist;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

}
