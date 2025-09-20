document.addEventListener('DOMContentLoaded', () => {
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
    const checkboxes = document.querySelectorAll('input[name="selectedIds"]');
    const deleteBtn = document.getElementById('deleteBtn');
  
    // 排他選択とボタン制御
    checkboxes.forEach((checkbox) => {
      checkbox.addEventListener("change", () => {
        if (checkbox.checked) {
          checkboxes.forEach((cb) => {
            if (cb !== checkbox) cb.checked = false;
          });
          deleteBtn.disabled = false;
        } else {
          const anyChecked = Array.from(checkboxes).some((cb) => cb.checked);
          if (!anyChecked) {
            deleteBtn.disabled = true;
          }
        }
      });
    });
  
    // submit時にhiddenで値を渡す
    ['deleteBtn'].forEach((id) => {
      const btn = document.getElementById(id);
      const form = btn.closest('form');
  
      form.addEventListener('submit', function (e) {
        const checked = document.querySelector('input[name="selectedIds"]:checked');
        if (checked) {
          const hidden = document.createElement('input');
          hidden.type = 'hidden';
          hidden.name = 'selectedIds';
          hidden.value = checked.value;
          this.appendChild(hidden);
        } else {
          e.preventDefault();
          alert('1つ選択してください');
        }
      });
    });
  });
  

  