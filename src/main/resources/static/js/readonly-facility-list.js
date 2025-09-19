document.addEventListener("DOMContentLoaded", function() {
    const toggleBtn = document.getElementById("toggleSearchBtn");
    const searchBox = document.getElementById("searchBox");

    toggleBtn.addEventListener("click", function() {
        if (searchBox.style.display === "none") {
            searchBox.style.display = "block";
            toggleBtn.textContent = "閉じる";
        } else {
            searchBox.style.display = "none";
            toggleBtn.textContent = "設備検索";
        }
    });

    //チェックボックス
    const checkboxes = document.querySelectorAll('input[type="checkbox"][name="selectedIds"]');

    checkboxes.forEach(cb => {
        cb.addEventListener("change", () => {
            if (cb.checked) {
                checkboxes.forEach(other => {
                    if (other !== cb) {
                        other.checked = false;
                    }
                });
            }
        });
    });
});