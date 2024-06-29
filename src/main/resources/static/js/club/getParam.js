function getClubName() {
    let url_href = window.location.href;
    let url = new URL(url_href);
    let clubName = url.searchParams.get("clubName");

    return clubName;
}