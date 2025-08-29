function toggleSearchForm() {
    const wrapper = document.getElementById("searchFormWrapper");
    wrapper.classList.toggle("hidden");
    btn.textContent = wrapper.classList.contains("hidden") ? "検索フォームを開く" : "検索フォームを閉じる";
}