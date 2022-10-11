const React = require("react");
const {createRoot} = require("react-dom/client");
const axios = require("axios").default;
const TrackList = require("./TrackList");
const AudioPlayer = require("./AudioPlayer");

class App extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            audioTracks: [],
            selectedSong: "",
            songPlaying: ""
        };
    }

    onPlayAction(track) {
        console.log(track)
        this.setState({songPlaying: track})
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
                <TrackList onPlayButton={this.onPlayAction.bind(this)} audioTracks={this.state.audioTracks} links={this.state.links}/>
                <AudioPlayer root={"http://localhost:8081/"} song={this.state.songPlaying}></AudioPlayer>
            </div>
        );
    }

}

class TrackDetails extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className={"trackDetails"}>

            </div>
        )
    }

}

createRoot(document.getElementById("react")).render(<App/>);