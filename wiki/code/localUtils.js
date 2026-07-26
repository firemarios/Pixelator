export class LocalUtils {
    static getHeader() {
        return `<div style="display: flex; align-items: center;">
                    <img alt="pixalator_icon" onclick="goTo('home')"
                        src="https://wsrv.nl/?url=https%3A%2F%2Fi.ibb.co%2F6cLD7M1s%2FChat-GPT-Image-Jul-1-2025-10-20-04-PM.png" />
                    <h1>Pixalator Wiki</h1>
                    </div>
                    <div style="display: flex; align-items: right; margin-left: auto; margin-right: 20px;">
                    <button onclick="window.open('https://modrinth.com/mod/pixelator')">Mod Page</button>
                    <div class="dropdown">
                        <button class="dropbtn">Pages</button>
                        <div class="dropdown-content">
                        <button onclick="goTo('home')" disabled="true">Home</button>
                        <button onclick="goTo('information')">Information</button>
                        <button onclick="goTo('changelog')">Changelog</button>
                        </div>
                    </div>
                    <button onclick="window.open('https://github.com/firemarios/Pixelator/issues')">Issues</button>
                    </div>`;
    }
}
function goTo(page) {
    document.location = "/Pixelator/wiki/" + page;
}
window.goTo = goTo;
function getAsset(asset) {
    console.log("Getting asset: " + asset);
    return fetch("/Pixelator/wiki/assets/" + asset);
}
window.getAsset = getAsset;
function handleScroll() {
    const header = document.querySelector('.header');
    const layout = document.getElementById('three-column-layout');
    if (!header || !layout)
        return;
    if (window.scrollY > 0) {
        header.classList.add('scrolled');
        layout.style.paddingTop = '60px';
    }
    else {
        header.classList.remove('scrolled');
        layout.style.paddingTop = '80px';
    }
}
window.addEventListener('scroll', handleScroll);
window.addEventListener('load', () => {
    handleScroll();
});
window.addEventListener('resize', () => {
    handleScroll();
});
function generateTOC() {
    const content = document.getElementById('toc-column');
    if (!content)
        return;
    const headings = content.querySelectorAll('h1, h2, h3, h4, h5, h6');
    const tocList = document.getElementById('toc-list');
    if (!tocList)
        return;
    tocList.innerHTML = '';
    let counters = [0, 0, 0, 0, 0, 0]; // counters for h1 to h6
    headings.forEach(heading => {
        if (!heading.id) {
            heading.id = 'heading-' + Math.random().toString(36).substr(2, 9);
        }
        const level = parseInt(heading.tagName.substring(1)) - 1; // 0-based index for counters
        if (counters[level] === undefined)
            return;
        counters[level]++;
        // Reset counters for deeper levels
        for (let i = level + 1; i < counters.length; i++) {
            counters[i] = 0;
        }
        // Build numbering string like 1, 1.1, 1.1.1 etc.
        let numberStr = '';
        for (let i = 0; i <= level; i++) {
            if (counters[i] === 0)
                continue;
            numberStr += counters[i] + '.';
        }
        const li = document.createElement('p');
        li.style.marginLeft = (level * 0) + 'px'; // indent based on level
        const a = document.createElement('a');
        a.href = '#' + heading.id;
        a.textContent = numberStr + ' ' + heading.textContent;
        a.style.cursor = 'pointer';
        a.style.textDecoration = 'none';
        a.style.fontSize = '18px';
        a.style.fontFamily = 'arial';
        a.style.color = 'white';
        a.style.marginLeft = '20px';
        a.addEventListener('click', (e) => {
            e.preventDefault();
            const target = document.getElementById(heading.id);
            if (target) {
                const header = document.querySelector('header');
                const headerHeight = header ? header.scrollHeight : 0;
                target.scrollIntoView({ behavior: 'smooth', block: 'start' });
                setTimeout(() => {
                    window.scrollBy(0, -(headerHeight));
                }, 100);
            }
        });
        li.appendChild(a);
        tocList.appendChild(li);
    });
}
window.addEventListener('load', generateTOC);
function stretchSidebar() {
    const sidebar = document.getElementById('sidebar-column');
    const fullHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    if (!sidebar)
        return;
    sidebar.style.height = fullHeight + 'px';
}
window.addEventListener('load', stretchSidebar);
window.addEventListener('resize', stretchSidebar);
window.addEventListener('scroll', stretchSidebar);
setHeader();
// Create a new link element
const link = document.createElement('link');
// Set attributes
link.rel = 'stylesheet';
link.href = '/Pixelator/wiki/global.css'; // path to your stylesheet
// Optionally, set other attributes
link.type = 'text/css';
// Append it to the <head> of the document
document.head.appendChild(link);
function setHeader() {
    const headerContainer = document.querySelector('.header');
    if (!headerContainer)
        return;
    headerContainer.innerHTML = LocalUtils.getHeader();
}
//# sourceMappingURL=localUtils.js.map