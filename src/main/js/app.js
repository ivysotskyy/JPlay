const React = require("react");
const {createRoot} = require("react-dom/client");
const axios = require("axios").default;
const TrackList = require("./TrackList");
const AudioPlayer = require("./AudioPlayer");
const TrackDetails = require("./TrackDetails");

class App extends React.Component {

    constructor(props) {
        super(props);
        this.track = {
            author: "",
            title: "",
            filePath: "",
            fileName: "",
            duration: 0,
            releaseDate: "",
            comment: "",
            genres: []
        }
        this.state = {
            audioTracks: [],
            selectedSong: "",
            songPlaying: this.track
        };
    }

    onPlayAction(track) {
        this.setState({songPlaying: track})
    }

    onSelected(track) {
        this.setState({
            selectedSong: track
        });
    }

    componentDidMount() {
        //Get api routes
        axios.get("/api/audioTracks?size=1000").then(response => {
            this.setState({
                links: response.data._links,
                audioTracks: response.data._embedded.audioTracks,
            });
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        /**
         * root: *The http-server hosting the music files.
         * because of chrome security reasons.
         */
        return (
            <div className={"wrapper"}>
                <TrackList handleTrackSelected={this.onSelected.bind(this)} onPlayButton={this.onPlayAction.bind(this)} audioTracks={this.state.audioTracks} links={this.state.links}/>
                <TrackDetails track={this.state.selectedSong}></TrackDetails>
                <AudioPlayer root={"http://localhost:8081/"} song={this.state.songPlaying}></AudioPlayer>
            </div>
        );
    }

}

createRoot(document.getElementById("react")).render(<App/>);